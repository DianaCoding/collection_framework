package tech.insight;
/**
 * @author: pengminwan
 * @CreateTime: 2026-03-03  11:58
 */
public class  MyHashMap<K,V> {
    Node<K,V>[] table = new Node[16];
    private int size = 0;


    public V put(K key, V value){
//        // 先检查key是否已经存在，存在则只需要将node的value
//        // 更改为当前的value，不存在则需要新建一个node，
//        // 并将k，v设置为当前值，再将node添加到table中
//        for (Node<K, V> kvNode : table) {
//            if (kvNode.key.equals(key)) {
//                V oldValue = kvNode.value;
//                kvNode.value = value;
//                return oldValue;
//            }
//        }
//        Node<K, V> newNode = new Node<>(key, value);
//        table.add(newNode);
        int keyIndex = indexOf(key);
        Node<K, V> head = table[keyIndex];
        if(head == null) {
            table[keyIndex] = new Node<>(key, value);
            size++;
            resizeIfNecessary();
            return  null;
        }
        while(true) {
            if(head.key.equals(key)) {
                V oldValue = head.value;
                head.value = value;
                return oldValue;
            }
            if(head.next == null) {
                head.next = new Node<>(key, value);
                size++;
                resizeIfNecessary();
                return null;
            }
            head = head.next;
        }
    }

    public V get(K key){
        int keyIndex = indexOf(key);
        Node<K, V> head = table[keyIndex];
        while(head != null) {
            if(head.key.equals(key)){
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V remove(K key){
        int keyIndex = indexOf(key);
        Node<K, V> head = table[keyIndex];
        if(head == null) {
            return null;
        }
        if(head.key.equals(key)) {
            table[keyIndex] = head.next;
            size--;
            return head.value;
        }
        Node<K,V> pre = head;
        Node<K, V> current = head.next;
        while (current != null) {
            if(current.key.equals(key)) {
                pre.next = current.next;
                size--;
                return current.value;
            }
            pre = pre.next;
            current = current.next;
        }

////        存在则需要把node从数组中移除并返回数组中该node的value
//        for (int i = 0; i < table.size(); i++) {
//            Node<K, V> kvNode = table.get(i);
//            if (kvNode.key.equals(key)) {
//                Node<K, V> removedNode = this.table.remove(i);
//                return removedNode.value;
//            }
//        }
        return null;
    }

    //    计算元素的角标
    private int indexOf(Object key){
        return key.hashCode() & table.length -1;
    }

//    通过扩容是无法解决某个node特别长的问题的
    private void resizeIfNecessary() {
        if(this.size < table.length * 0.75) {
            return;
        }
        Node<K, V>[] newTable = new Node[table.length * 2];
        for (Node<K, V> head : this.table) {
            if(head == null) {
                continue;
            }
            Node<K, V> current = head;
            while(current != null) {
                int newIndex = current.key.hashCode() & newTable.length-1;
                if (newTable[newIndex] == null) {
                    newTable[newIndex] = current;
                    Node<K, V> next = current.next;
                    current.next = null;
                    current = next;
                } else {
                    Node<K, V> next = current.next;
                    current.next = newTable[newIndex];
                    newTable[newIndex] = current;
                    current = next;
                }
            }
        }
        this.table = newTable;
        System.out.println("扩容到 " + this.table.length );
    }
    public int size() {
        return this.size;
    }

    // 通过node将key和value关联起来
    class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        Node<K, V> pre;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}