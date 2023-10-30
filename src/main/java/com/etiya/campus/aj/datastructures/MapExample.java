package com.etiya.campus.aj.datastructures;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapExample {
    enum Types {PRODUCT,ORDER,ACCOUNT,CUSTOMER};

    public static void main(String[] args){
        //concurrentHashMapExample();
        //concurrentSkipListMapExample();
        //numMapExample();
        //hashMapExample();
        //hashtableExample();
        //identityHashMapExample();
        //linkedHashMapExample();
        propertiesExample();
        //treeMapExample();
        //weakHashMapExample();
    }

    private static void concurrentHashMapExample(){
        /*
        Thread safe
        Değişiklik sırasında bütünü değil bir kısımı locklanır
         */
        ConcurrentHashMap<String ,Integer> map = new ConcurrentHashMap<>();
        for(int i = 1;i<101;i++) map.put(String.valueOf(i),new Integer(i));
        System.out.println(map);
    }

    private static void enumMapExample(){
        /*
        Enum tipi key kullanmak içindir
        Bütün keyler tanımlanan enumdan olmalıdır
         */
        EnumMap<Types ,Integer> map = new EnumMap<>(Types.class);
        map.put(Types.CUSTOMER,1);
        map.put(Types.ACCOUNT,2);
    }

    private static void hashMapExample(){
        /*
        Ekleme sırasını korumaz
        Thread-safe değildir.
         */
        HashMap<String ,Integer> map = new HashMap<>();
        for(int i = 1;i<101;i++) map.put(String.valueOf(i),new Integer(i));
        System.out.println(map);
        for(String key:map.keySet()){
            map.get(key);
        }
    }

    private static void hashtableExample(){
        /*
        Lişstelerin dizisidir
        Collections'dan önce tasarladığı için modası geçmiş diyebiliriz
         */
        Hashtable<String ,Integer> map = new Hashtable<>();
        for(int i = 1;i<101;i++) map.put(String.valueOf(i),new Integer(i));
        System.out.println(map);
    }

    private static void identityHashMapExample(){
        /*
        Eşitliği karşılaştırıken a.equals(b) yerine a==b kullanır
        Bu nedenle .equals yerine referans karşılaştırması == gerekli ise kullanılabilir
        Synchronized değildir.
         */
        IdentityHashMap<String ,Integer> map = new IdentityHashMap<>();
        for(int i = 1;i<101;i++) map.put(String.valueOf(i),new Integer(i));
        System.out.println(map);
    }

    private static void linkedHashMapExample(){
        /*
        HashMap'ten farklı olarak elemanlar için linked list kullanır
        Bu linked list sıralamayı sağlar yani eklenme sırasının korunmasını sağlar
        Synchronized değildir.
         */
        LinkedHashMap<String ,Integer> map = new LinkedHashMap<>();
        for(int i = 1;i<101;i++) map.put(String.valueOf(i),new Integer(i));
        System.out.println(map);
    }

    private static void propertiesExample(){
        /*-
        Property kullanımı içindir
        Key ve value string tipindedir
         */
        Properties properties = System.getProperties();
        Iterator it = properties.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry entry = (Map.Entry)it.next();
            System.out.println(entry.getKey()+" - "+entry.getValue());
        }
    }

    private static void treeMapExample(){
        /*
        Nesnelerin doğal sıralaması veya tanımlanan Comparator ile sıralama sağlanır
        Synchronized değildir.
         */
        TreeMap<String ,Integer> map = new TreeMap<>();
        for(int i = 1;i<101;i++) map.put(String.valueOf(i),new Integer(i));
        System.out.println(map);
    }

    private static void weakHashMapExample(){
        /*
        Weak keylere sahip bir Map. Kullanılmayan entryler otomatik olarak çıkarılır yani
        bu map içinde mapping olması garbage collector tarafından gözardı edilmesine neden olmaz.
        Synchronized değildir.
         */
        WeakHashMap<String ,Integer> map = new WeakHashMap<>();
        for(int i = 1;i<101;i++) map.put(String.valueOf(i),new Integer(i));
        System.out.println(map);
    }
}
