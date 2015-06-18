package com.github.booknara.appcategory.comparator;


import com.github.booknara.appcategory.vo.PackageVO;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractPackageComparator implements Comparator<PackageVO> {
    /**
     * Sort an array of packages.
     * <p>
     * This method uses {@link Arrays#sort(Object[], Comparator)}
     * and returns the original array.
     *
     * @param packages The packages to sort, may be null
     * @return The sorted array
     * @since 2.0
     */
    public PackageVO[] sort(PackageVO... packages) {
        if (packages != null) {
            Arrays.sort(packages, this);
        }
        return packages;
    }

    /**
     * Sort a List of packages.
     * <p>
     * This method uses {@link Collections#sort(List, Comparator)}
     * and returns the original list.
     *
     * @param packages The packages to sort, may be null
     * @return The sorted list
     * @since 2.0
     */
    public List<PackageVO> sort(List<PackageVO> packages) {
        if (packages != null) {
            Collections.sort(packages, this);
        }
        return packages;
    }

    /**
     * String representation of this packages comparator.
     *
     * @return String representation of this packages comparator
     */
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
