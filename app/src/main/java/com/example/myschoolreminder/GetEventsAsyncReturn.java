/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 13.11.2019
 * Description : Interface returning content
 */

package com.example.myschoolreminder;

import java.util.List;

/**
 * Interface returning content
 */
public interface GetEventsAsyncReturn {
    public void returnEvents(List<Event> output);
}
