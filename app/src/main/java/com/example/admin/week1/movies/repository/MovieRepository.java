package com.example.admin.week1.movies.repository;
/* *
 *  Created by JAY on 18/06/2018
 */

public interface MovieRepository {
    void getDataFromNetWork(DataListener listener);

    void getDataFromLocal(DataListener listener);
}
