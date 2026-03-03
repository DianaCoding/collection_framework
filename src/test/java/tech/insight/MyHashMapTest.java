package tech.insight;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author: pengminwan
 * @CreateTime: 2026-03-03  12:26
 */
public class MyHashMapTest {
    @Test
    public void testApi(){
        MyHashMap<String,String> myHashMap = new MyHashMap<>();
        int count  = 100000;
        for (int i = 0; i < count; i++) {
            myHashMap.put(String.valueOf( i),String.valueOf(i));
        }
        assertEquals(count, myHashMap.size());

        for (int i = 0; i < count; i++) {
            assertEquals(String.valueOf(i), myHashMap.get(String.valueOf(i)));
        }

        myHashMap.remove("1");
        assertNull(myHashMap.get("1"));
        assertEquals(count-1, myHashMap.size());

    }

}