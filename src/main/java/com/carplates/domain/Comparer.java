/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carplates.domain;

/**
 *
 * @author rychu
 */
public class Comparer {

    public static <T> int compare(T first, T second) {
        if (first == null && second == null) {
            return 0;
        } else if (first == null) {
            return -1;
        } else if (second == null) {
            return 1;
        } else {
            return first.toString().compareTo(second.toString());
        }
    }
}
