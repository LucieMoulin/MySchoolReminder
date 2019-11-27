/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 13.11.2019
 * Description : Interface returning content
 */

package com.example.myschoolreminder.ObjectsAsyncReturnInterfaces;

import com.example.myschoolreminder.Objects.Class;

import java.util.List;

/**
 * Interface returning content
 */
public interface GetClassesAsyncReturn {
    public void returnClasses(List<Class> output);
}
