package com.pags.get.date;

class Preson {
    private int age = 10;

    public Preson() {
        System.out.println("初始化的年龄为：" + age);
    }

    public int GetAge(int age) {
        this.age = age;
        return this.age;
    }
}

class Country {
    String name;

    void value() {
        name = "CHINA";
    }
}

class city extends Country {
    String name;

    void value() {
        name = "shanghai";
        super.value();

    }
}

public class everyClass {
    private static int intStatic = 222;
    private static String stringStatic = "old string";
    private static StringBuilder stringbuilderStatic = new StringBuilder("old stringbuilder");

    public static void method(int intStatic) {
        intStatic = 777;
    }

    public static void method() {
        intStatic = 888;
    }

    public static void method(String stringStatic) {
        stringStatic = "new stringStatus";
    }

    public static void method(StringBuilder stringbuilderstatic1, StringBuilder stringbuilderstatic2) {
        stringbuilderstatic1.append(".method.first-");
        stringbuilderstatic2.append("method.second-");
        //引用重新赋值
        stringbuilderstatic1 = new StringBuilder("new stringbuilder");
        stringbuilderstatic1.append("new method's append");
    }

    //可变参数
    public static void listUser(Object... arges) {
        System.out.println(arges.length);
    }

    //有明确返回值的方法调用
    public static int sum(int a, int b) {
        int c = a + b;
        return c;
    }


    public static void main(String[] args) {

        WcGzMethod wcg=new WcGzMethod();

        WcGzMethod wcgz = new WcGzMethod(20,"王小狗");
        wcgz.Show();


//        System.out.println("==============================================");
//        sum(10, 20);
//        System.out.println(sum(10,20));
//        int sum1=sum(10,20);
//        System.out.println(sum1);
//        System.out.println("==============================================");
//        Preson harry = new Preson();
//        int harryAge = harry.GetAge(18);
//        System.out.println("harry's age is " + harryAge);
//        System.out.println("==============================================");
//        city c = new city();
//        c.value();
//        System.out.println(c.name);
//        System.out.println("==============================================");
//        method(intStatic);
//        method(stringStatic);
//        method(stringbuilderStatic,stringbuilderStatic);
//        System.out.println(intStatic);
//        method();
//        System.out.println(intStatic);
//        System.out.println(stringStatic);
//        System.out.println(stringbuilderStatic);
//        System.out.println("==============================================");
//        listUser(1,2,3);
//        listUser(new int[]{1,2,3});
//        listUser(3,new String[]{"1","2"});
//        listUser(new Integer[]{1,2,3});
//        listUser(3,new Integer[] {1,2,3});

    }

}
