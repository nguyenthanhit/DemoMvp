package com.example.admin.week1.detail;
/* *
 *  Created by JAY on 18/06/2018
 */

public interface Contract {

    interface DetailView {
        void showLoading();
    }

    interface DetailPresenter {
        void getData();
    }

}
