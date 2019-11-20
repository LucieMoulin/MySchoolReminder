/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 20.11.2019
 * Description : Interface returning content
 */

package com.example.myschoolreminder;

import java.util.List;

/**
 * Interface returning content
 */
public interface GetCheckListElementsAsyncReturn {
    public void returnCheckListElements(List<CheckListElement> output);
}
