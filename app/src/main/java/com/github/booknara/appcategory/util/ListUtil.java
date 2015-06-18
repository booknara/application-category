package com.github.booknara.appcategory.util;


import com.github.booknara.appcategory.vo.PackageVO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * List
 *
 * @author Daniel Han (@daniel_booknara)
 * @since 2014/05/06
 * @version 1.0.0
 *
 */
public class ListUtil {
    private ListUtil() { }    // This constructor will never be invoked
    
	private static final String CNAME = ListUtil.class.getSimpleName();

    public static List<PackageVO> removeDuplicates(List<PackageVO> list) {
        Set set = new HashSet();
        List uniqueList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                uniqueList.add(element);
        }

//        list.clear();
//        list.addAll(uniqueList);
        return uniqueList;
    }
}