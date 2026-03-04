package tech.insight;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author: pengminwan
 * @CreateTime: 2026-03-04  10:47
 */
public class ListTest {
    @Test
    public void operateTest() {
//        List<String> list = new ArrayList<>();
        List<String> list = new LinkedList<>();
        for(int i = 0; i < 30; i++) {
            list.add(String.valueOf(i));
        }
        assertEquals(30, list.size());
        list.remove(15);
        list.remove(18);
        assertEquals(28, list.size());
        assertEquals("16",list.get(15));
        assertEquals("24",list.get(22));

        list.forEach(System.out::println);
    }
}