package com.dangnian.springboot.common.util;


import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * <p>集合类基础工具类<p/>
 *
 * @Author nian.qiu
 * @Date 2018/8/13 14:36
 * @Version 1.0.0
 */
public class CollectionBaseUtils {


    /**
     * 获得空集合
     *
     * @return
     */
    public static Collection emptyCollection(){
      return  CollectionUtils.EMPTY_COLLECTION;
    }

    /**
     * 判断对象是空
     *
     * @param coll
     * @return true: 是空 false: 不是空
     */
    public static boolean isEmpty(Collection coll) {
        return  CollectionUtils.isEmpty(coll);
    }

    /**
     * 判断集合对象不是空
     *
     * @param coll 集合对象
     * @return true: 集合对象不为空 false: 集合对象为空
     */
    public static boolean isNotEmpty(Collection coll) {
        return CollectionUtils.isNotEmpty(coll);
    }

    /**
     * 两个集合的并集
     *
     * @param a
     * @param b
     * @return  并集
     */
    public  static <C extends Collection> C union(final C a, final C b){
        return (C)CollectionUtils.union(a,b);
    }

    /**
     * 两个集合的交集
     *
     * @param a
     * @param b
     * @return 交集
     */
    public static <C extends Collection> C intersection(final C a, final C b) {
        return (C)CollectionUtils.intersection(a,b);
    }

    /**
     * 集合的差集
     *
     * @param a
     * @param b
     * @return 差集
     */
    public static <C extends Collection> C subtract(final C a, final C b){
        return (C)CollectionUtils.subtract(a,b);
    }

    /**
     * 非交集
     *
     * @param a
     * @param b
     * @return 非交集
     */
    public static <C extends Collection> C disjunction(final C a, final C b) {
        return (C)CollectionUtils.disjunction(a,b);
    }

    /**
     * 是否包含某个元素
     * @param a
     * @param b
     * @param <C>
     * @return
     */
    public static <C> boolean contains(Collection<C> a, final C b) {
        return CollectionUtils.isNotEmpty(a) && a.contains(b);
    }

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<>();
    }

    public static <E> ArrayList<E> newArrayList(E... elements) {
        ArrayList<E> list = new ArrayList<>();
        Collections.addAll(list, elements);
        return list;
    }

    public static List<Long> convertStringListToLongList(List<String> stringList) {
        List<Long> longList = new ArrayList<>();
        for (String pkId : stringList) {
            longList.add(Long.valueOf(pkId));
        }
        return longList;
    }

    public static <E> Set<E> newSet(E... elements) {
        Set<E> set = new HashSet<>();
        Collections.addAll(set, elements);
        return set;
    }

    public static int size(Collection coll) {
        return CollectionUtils.isEmpty(coll)?0:CollectionUtils.size(coll);
    }

    public static <E> E findAndRemoveFirst(Collection<E> collection) {
        for (Iterator it = collection.iterator(); it.hasNext();) {
            E value = (E)it.next();
            it.remove();
            return value;
        }
        return null;
    }

    public static <T> List<T> emptyList() {
        return Collections.emptyList();
    }
}