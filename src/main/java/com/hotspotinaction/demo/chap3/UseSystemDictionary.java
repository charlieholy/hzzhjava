package com.hotspotinaction.demo.chap3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import sun.jvm.hotspot.memory.SystemDictionary;
import sun.jvm.hotspot.oops.InstanceKlass;
import sun.jvm.hotspot.oops.Klass;
import sun.jvm.hotspot.oops.Oop;
import sun.jvm.hotspot.oops.Symbol;
import sun.jvm.hotspot.runtime.VM;
import sun.jvm.hotspot.tools.Tool;

//java -cp sa-jdi.jar sun.jvm.hotspot.HSDB
public class UseSystemDictionary extends Tool {
    static {
        VM.registerVMInitializedObserver(new Observer() {
            @Override
            public void update(Observable o, Object data) {
                initialize();
            }
        });
    }

    private static synchronized void initialize() {
        klasses = null;
    }

    // Instance klass array sorted by name.
    private static InstanceKlass[] klasses;

    public static void main(String[] args) {
        // args - ����pid
        UseSystemDictionary usd = new UseSystemDictionary();
        usd.execute(args);
        usd.stop();
    }

    @Override
    public void run() {
        PrintStream tty = null;
        String dir = "E:\\log\\";
        File file = new File(dir + "UseSystemDictionary-" + System.currentTimeMillis() + ".log");
        try {
            tty = new PrintStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // ҵ���װ
        listInstanceKlasses(tty);
    }

    /**
     * �г�ϵͳ���ص������ಢ�������
     *
     * @author Chen Tao
     */
    public static synchronized void listInstanceKlasses(PrintStream tty) {
        // ���ϵͳ�ֵ䲢�õ������Ѽ�����
        final Vector tmp = new Vector();
        SystemDictionary sysDict = VM.getVM().getSystemDictionary();

        sysDict.classesDo(new SystemDictionary.ClassVisitor() {
            @Override
            public void visit(Klass k) {
                if (k instanceof InstanceKlass) {
                    InstanceKlass ik = (InstanceKlass) k;
                    tmp.add(ik);
                }
            }
        });

        // �������ఴ������
        Object[] tmpArray = tmp.toArray();
        klasses = new InstanceKlass[tmpArray.length];
        System.arraycopy(tmpArray, 0, klasses, 0, tmpArray.length);
        Arrays.sort(klasses, new Comparator() {
            public int compare(Object o1, Object o2) {
                InstanceKlass k1 = (InstanceKlass) o1;
                InstanceKlass k2 = (InstanceKlass) o2;
                Symbol s1 = k1.getName();
                Symbol s2 = k2.getName();
                return s1.asString().compareTo(s2.asString());
            }
        });

        // �����tty
        Symbol s = null;
        Symbol s2 = null;
        for (InstanceKlass ik : klasses) {
            s = ik.getName();
            s.printValueOn(tty);
            ik.getClassLoader();
            Oop loader = ik.getClassLoader();
            tty.println(loader != null ? "     class loader: " + loader.getKlass().getName().asString() + " @ " + loader.getHandle() : "<bootstrap>");
        }
    }

    public static synchronized InstanceKlass[] getAllInstanceKlasses() {
        if (klasses != null) {
            return klasses;
        }

        final Vector tmp = new Vector();
        SystemDictionary sysDict = VM.getVM().getSystemDictionary();
        sysDict.classesDo(new SystemDictionary.ClassVisitor() {
            @Override
            public void visit(Klass k) {
                if (k instanceof InstanceKlass) {
                    InstanceKlass ik = (InstanceKlass) k;
                    tmp.add(ik);
                }
            }
        });

        Object[] tmpArray = tmp.toArray();
        klasses = new InstanceKlass[tmpArray.length];
        System.arraycopy(tmpArray, 0, klasses, 0, tmpArray.length);
        Arrays.sort(klasses, new Comparator() {
            public int compare(Object o1, Object o2) {
                InstanceKlass k1 = (InstanceKlass) o1;
                InstanceKlass k2 = (InstanceKlass) o2;
                Symbol s1 = k1.getName();
                Symbol s2 = k2.getName();
                return s1.asString().compareTo(s2.asString());
            }
        });
        return klasses;
    }

    public static InstanceKlass[] findInstanceKlasses(String namePart) {
        namePart = namePart.replace('.', '/');
        InstanceKlass[] tmpKlasses = getAllInstanceKlasses();

        Vector tmp = new Vector();
        for (int i = 0; i < tmpKlasses.length; i++) {
            String name = tmpKlasses[i].getName().asString();
            if (name.indexOf(namePart) != -1) {
                tmp.add(tmpKlasses[i]);
            }
        }

        Object[] tmpArray = tmp.toArray();
        InstanceKlass[] searchResult = new InstanceKlass[tmpArray.length];
        System.arraycopy(tmpArray, 0, searchResult, 0, tmpArray.length);
        return searchResult;
    }

    public static InstanceKlass findInstanceKlass(String className) {
        className = className.replace('.', '/');
        SystemDictionary sysDict = VM.getVM().getSystemDictionary();

        Klass klass = sysDict.find(className, null, null);
        if (klass != null) {
            return (InstanceKlass) klass;
        }

        klass = sysDict.find(className, sysDict.javaSystemLoader(), null);
        if (klass != null) {
            return (InstanceKlass) klass;
        }

        InstanceKlass[] tmpKlasses = getAllInstanceKlasses();
        int low = 0;
        int high = tmpKlasses.length - 1;

        int mid = -1;
        while (low <= high) {
            mid = (low + high) >> 1;
            InstanceKlass midVal = tmpKlasses[mid];
            int cmp = midVal.getName().asString().compareTo(className);

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return tmpKlasses[mid];
            }
        }
        return null;
    }
}
