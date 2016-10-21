package com.lirenkj.rxjavastudy.study;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * RxMap
 * <p>
 * Created by Alexander on 2016/10/21.
 */
@SuppressWarnings("unused")
public class RxMap {
    /**
     * Map - 将对象进行1-1的转换
     *
     * @param location String类型的输入数据
     */
    private static void map(String location) {
        Observable.just(location)
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String file) {
                        return BitmapFactory.decodeFile(file);
                    }
                })
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        // display bitmap
                    }
                });
    }

    private static void flatMap(Student[] students) {
        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Student.Course>>() {
                    @Override
                    public Observable<Student.Course> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                })
                .subscribe(new Subscriber<Student.Course>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Student.Course course) {
                        Log.e("Name", course.getName());
                    }
                });
    }

    private class Student {
        private String mName;
        private Course[] mCourses;

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

        public Course[] getCourses() {
            return mCourses;
        }

        public void setCourses(Course[] courses) {
            mCourses = courses;
        }

        class Course {
            private String mName;

            public String getName() {
                return mName;
            }

            public void setName(String name) {
                mName = name;
            }
        }
    }
}
