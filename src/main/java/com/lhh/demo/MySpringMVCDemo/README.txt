这个包是用来简单模拟SpringMVC工作流程

包含注解：@MyCon, @MyServiceAnno, @MyRepository, @MyRequestMapping, @MyScanner，@MyQualifer
    @MyCon:标识一个类是一个Controller
    @MyServiceAnno：标识一个类是一个Service
    @MyRepository:标识一个类是一个Dao
    @MyRequestMappin:标识一个类/一个方法是一个Action
    @MyScanner:标识扫描的自定义类的包路径
    @MyQualifer:依赖注入

包含类：MyController, MyDao, MyService, MyServiceImpl，MyDispatherServlet
    MyController:控制类，被@MyCon注解标识一个Controller,被@MyRequestMapping注解标识action的虚拟根路径
    MyDao:Dao层，被@MyRepository注解
    MyServiceImpl:service,被@MyServiceAnno注解
    MyDispatherServlet:请求转发servlet,也是自定义的容器类


工作机制：
    MyDispatherServlet对@MyScanner注释的类进行解析获取扫描包路径basePackage
    MyDispatherServlet扫描包路径得到包中所有文件，通过类加载器对@MyCon,@MyServiceAnno,@MyRepository注解的类进行实例化，注入自身容器Map维护
    MyDispatherServlet遍历容器Map的所有实例对象，对@MyQualifer注释的属性进行注入上一步实例化的对象
    MyDispatherServlet遍历所有@Mycon注释的类，将其中@MyRequestMapping注释方法中的路径与方法关联
