/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 13.11.2019
 * Description : Interface returning content
 */

package com.example.myschoolreminder.ObjectsAsyncReturnInterfaces;

import com.example.myschoolreminder.Objects.Repetition;

import java.util.List;

/**
 * Interface returning content
 */
public interface GetRepetitionsAsyncReturn {
    public void returnRepetitions(List<Repetition> output);
}
