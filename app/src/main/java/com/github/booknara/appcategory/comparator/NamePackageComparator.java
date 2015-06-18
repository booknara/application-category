package com.github.booknara.appcategory.comparator;


import com.github.booknara.appcategory.vo.PackageVO;

import java.io.Serializable;
import java.util.Comparator;


public class NamePackageComparator extends AbstractPackageComparator implements Serializable {
    private static final String CNAME = NamePackageComparator.class.getSimpleName();

    /** Case-sensitive name comparator instance  */
    public static final Comparator<PackageVO> NAME_COMPARATOR = new NamePackageComparator();

    /** Reverse case-sensitive name comparator instance */
    public static final Comparator<PackageVO> NAME_REVERSE = new ReverseComparator(NAME_COMPARATOR);

    /** Case-insensitive name comparator instance  */
    public static final Comparator<PackageVO> NAME_INSENSITIVE_COMPARATOR = new NamePackageComparator(PackageCase.INSENSITIVE);

    /** Reverse case-insensitive name comparator instance  */
    public static final Comparator<PackageVO> NAME_INSENSITIVE_REVERSE = new ReverseComparator(NAME_INSENSITIVE_COMPARATOR);

    /** Whether the comparison is case sensitive. */
    private final PackageCase caseSensitivity;
	
    /**
     * Construct a case sensitive package name comparator instance.
     */
    public NamePackageComparator() {
        this.caseSensitivity = PackageCase.SENSITIVE;
    }
    
    /**
     * Construct a file name comparator instance with the specified case-sensitivity.
     *
     * @param caseSensitivity  how to handle case sensitivity, null means case-sensitive
     */
    public NamePackageComparator(PackageCase caseSensitivity) {
        this.caseSensitivity = caseSensitivity == null ? PackageCase.SENSITIVE : caseSensitivity;
    }

    /**
     * Compare the names of two files with the specified case sensitivity.
     * 
     * @param package1 The first file to compare
     * @param package2 The second file to compare
     * @return a negative value if the first file's name
     * is less than the second, zero if the names are the
     * same and a positive value if the first files name
     * is greater than the second file.
     */
    public int compare(PackageVO package1, PackageVO package2) {
        return caseSensitivity.checkCompareTo(package1.appname, package2.appname);
    }
    
    /**
     * String representation of this package comparator.
     *
     * @return String representation of this package comparator
     */
    @Override
    public String toString() {
        return super.toString() + "[caseSensitivity=" + caseSensitivity + "]";
    }
}
