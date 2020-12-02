package com.pags.get.date;

public class WcGzMethod {
    private String name;

    private Integer  age;
    public WcGzMethod(){
        System.out.println("无参构造方法被调用");
    }
    public WcGzMethod(Integer age,String name){
        this.age=age;
        this.name=name;
    }
    public void Show(){
        System.out.println("name:"+this.name+"age:"+this.age);
    }
}

