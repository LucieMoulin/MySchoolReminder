/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 20.11.2019
 * Description : Interface returning content
 */

package com.example.myschoolreminder.ObjectsAsyncReturnInterfaces;

import com.example.myschoolreminder.Objects.CheckListElement;

import java.util.List;

/**
 * Interface returning content
 */
public interface GetCheckListElementsAsyncReturn {
    public void returnCheckListElements(List<CheckListElement> output);
}
