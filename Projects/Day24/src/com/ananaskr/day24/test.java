package com.ananaskr.day24;

import java.io.*;
import java.lang.reflect.*;

class Invoker implements Serializable {

    private String c;
    private String m;
    private String[] a;

    public Invoker(String c, String m, String[] a) {
        this.c = c;
        this.m = m;
        this.a = a;
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        ois.defaultReadObject();
        Class clazz = Class.forName(this.c);
        Object obj = clazz.getConstructor(String[].class).newInstance(new Object[]{this.a});
        Method meth = clazz.getMethod(this.m);
        meth.invoke(obj, null);
    }
}
class User implements Serializable {
    private String name;
    private String email;
    transient private Object password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        password = stream.readObject();
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + ", email='" + email + "'}";
    }
}
