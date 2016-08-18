/**
 * 
 */
package com.cf.code.test;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;

import java.io.File;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public class GroovyTest {
    
    public String myfn(String name){
        return "-----"+name;
    }

    public static void main(String[] args) throws Exception{
        test3();
    }
    
    public static void test1(){
        Binding binding = new Binding();
        binding.setVariable("var", 5);
        GroovyShell gs = new GroovyShell(binding);
        Object value = gs.evaluate("println 'Hello Groovy !';abc=123;return var*10");//执行groovyshell脚本
        System.out.println(value.equals(50));
        System.out.println(binding.getVariable("abc").equals(123));
    }
    
    public static void test2()throws Exception{
        ClassLoader parent = ClassLoader.getSystemClassLoader();
        GroovyClassLoader loader = new GroovyClassLoader(parent);
        Class groovyClass = loader.parseClass(new File("E:\\git\\demo\\src\\test\\resources\\GroovyDemo.groovy"));
        GroovyObject groovyObject = (GroovyObject)groovyClass.newInstance();
        Object[] param = {123,321};
        int res = (int) groovyObject.invokeMethod("add", param);
        System.out.println("res="+res);
    }
    
    public static void test3()throws Exception{
        String path = "E:\\git\\demo\\src\\test\\resources\\";
        GroovyScriptEngine gse = new GroovyScriptEngine(path);
        Binding binding = new Binding();
        binding.setVariable("input", "Groovy");
        gse.run("Test.groovy", binding);
        System.out.println(binding.getVariable("output"));
    }
    
}
