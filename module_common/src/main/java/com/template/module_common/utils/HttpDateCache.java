package com.template.module_common.utils;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;


@Entity(tableName="http_cache_table")
class HttpDateCache implements Parcelable {


    protected HttpDateCache(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HttpDateCache> CREATOR = new Creator<HttpDateCache>() {
        @Override
        public HttpDateCache createFromParcel(Parcel in) {
            return new HttpDateCache(in);
        }

        @Override
        public HttpDateCache[] newArray(int size) {
            return new HttpDateCache[size];
        }
    };
}
