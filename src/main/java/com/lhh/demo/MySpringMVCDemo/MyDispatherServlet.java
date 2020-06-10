package com.lhh.demo.MySpringMVCDemo;

import org.springframework.beans.SimpleTypeConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lai.huihui on 2020/6/10.
 */
@Component
@WebServlet(urlPatterns = "/*", loadOnStartup = 1)
public class MyDispatherServlet extends HttpServlet {
    private String basePackage = "";

    private List<String> packagesName = new ArrayList<>();

    private Map<String, Object> instanceMap = new HashMap<>();

    private Map<String, String> nameMap = new HashMap<>();

    private Map<String, Method> urlMethodMap = new HashMap<>();

    private Map<Method, String> methodPackageMap = new HashMap<>();

    private static SimpleTypeConverter converter = new SimpleTypeConverter();

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.basePackage = "com.lhh.demo.MySpringMVCDemo";
        scanBasePackage(basePackage);
        try {
            instance(packagesName);
            springIoc();
            handleUrlMethodMap();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void init(Class clazz) throws Exception{
        Annotation anno =  clazz.getAnnotation(MyScanner.class);
        if (anno == null) {
            throw new Exception("非扫描类");
        }
        this.basePackage = ((MyScanner)anno).path();
        scanBasePackage(basePackage);
        instance(packagesName);
        springIoc();
        handleUrlMethodMap();
    }

    /*
    *@description:通过包路径扫描包，得到路径下的所有class文件
    *@method:
    *@params:
    * * @param null
    *@return:
    *@time: 2020/6/10 15:14
    */
    private void scanBasePackage(String basePackage) {
        final URL url = this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.", "/"));
        final File basePackageFile = new File(url.getPath());
        System.out.println("扫描到的文件是:" + basePackageFile.getName());
        final File[] files = basePackageFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                scanBasePackage(basePackage + "." + file.getName());
            } else if (file.isFile()) {
                packagesName.add(basePackage + "." + file.getName().split("\\.")[0]);
            }
        }
    }

    /*
    *@description:依赖注入，向容器中Bean实例中含有MyQualifer注解的属性进行依赖注入
    *@method:
    *@params:
    * * @param null
    *@return:
    *@time: 2020/6/10 15:15
    */
    private void springIoc() throws IllegalAccessException {
        for (Map.Entry<String, Object> annotationNameAndInstance : instanceMap.entrySet()) {
            final Field[] fields = annotationNameAndInstance.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(MyQualifer.class)) {
                    final String myQualiferName = field.getAnnotation(MyQualifer.class).value();
                    field.setAccessible(true);
                    field.set(annotationNameAndInstance.getValue(), instanceMap.get(myQualiferName));
                }
            }
        }
    }
    /*
    *@description:实例化自定义类的Bean注解：MyCon,MyServiceAnno,MyRepository
    *@method:
    *@params:
    * * @param null
    *@return:
    *@time: 2020/6/10 15:16
    */
    private void instance(List<String> packageNames) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        if (packageNames.size() < 1) {
            return;
        }
        for (String packageName : packageNames) {
            Class<?> fileClass = Class.forName(packageName);
            if (fileClass.isAnnotationPresent(MyCon.class)) {
                MyCon myController = fileClass.getAnnotation(MyCon.class);
                final String controllerName = myController.value();
                instanceMap.put(controllerName, fileClass.newInstance());
                nameMap.put(packageName, controllerName);
                System.out.println("Controller:" + packageName + "name" + controllerName);
            } else if (fileClass.isAnnotationPresent(MyServiceAnno.class)) {
                final MyServiceAnno service = fileClass.getAnnotation(MyServiceAnno.class);
                final String serviceName = service.value();
                instanceMap.put(serviceName, fileClass.newInstance());
                nameMap.put(packageName, serviceName);
            } else if (fileClass.isAnnotationPresent(MyRepository.class)) {
                final MyRepository repository = fileClass.getAnnotation(MyRepository.class);
                final String repositoryName = repository.value();
                instanceMap.put(repositoryName, fileClass.newInstance());
                nameMap.put(packageName, repositoryName);
            }
        }
    }
    /*
    *@description:找到自定义注解MyRequestMappin下的所有方法，与注解的url一一对应
    *@method:
    *@params:
    * * @param null
    *@return:
    *@time: 2020/6/10 15:18
    */
    private void handleUrlMethodMap() throws ClassNotFoundException {

        if (packagesName.size() < 1) {
            return;
        }

        for (String packageName : packagesName) {

            final Class<?> fileClass = Class.forName(packageName);
            if (!fileClass.isAnnotationPresent(MyCon.class)) {
                continue;
            }
            final Method[] methods = fileClass.getMethods();

            final StringBuffer baseUrl = new StringBuffer();

            if (fileClass.isAnnotationPresent(MyRequestMapping.class)) {

                MyRequestMapping myRequest = (MyRequestMapping) fileClass.getAnnotation(MyRequestMapping.class);
                baseUrl.append(myRequest.value());
            }

            for (Method method : methods) {
                if (method.isAnnotationPresent(MyRequestMapping.class)) {
                    final MyRequestMapping myrequest = method.getAnnotation(MyRequestMapping.class);
                    String methodUrl = baseUrl.toString().concat(myrequest.value());

                    urlMethodMap.put(methodUrl, method);
                    methodPackageMap.put(method, packageName);
                }
            }
        }
    }

    public Object invokeFunc(String url, Object[] args) throws Exception{
        if (StringUtils.isEmpty(url)) {
            return "404";
        }
        String requestUrl = url.replaceAll("http://localhost:8080", "");
        System.out.println(requestUrl);
        Method m = urlMethodMap.get(requestUrl);
        if (m != null) {
            String packageName = methodPackageMap.get(m);
            String conName = nameMap.get(packageName);
            Object con = instanceMap.get(conName);
            return m.invoke(con, args);
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        System.out.println("do post 111");
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        String requestUrl = url.replaceAll(contextPath, "");
        System.out.println(requestUrl);
        Map<String, String[]> params = req.getParameterMap();
        Method m = urlMethodMap.get(requestUrl);
        if (m != null) {
            String packageName = methodPackageMap.get(m);
            String conName = nameMap.get(packageName);
            Object con = instanceMap.get(conName);
            m.setAccessible(true);
            try {
                Parameter[] ps = m.getParameters();
                List<Object> as = new ArrayList<>();
                for (Parameter p : ps) {
                    String pName = p.getName();
                    Class clazz = p.getType();
                    String[] value = params.get(pName);
                    Object o = converter.convertIfNecessary(value, clazz);
                    as.add(o);
                }
                Object result =  m.invoke(con, as.toArray());
                if (result != null) {
                    resp.getWriter().write(result.toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
