package com.example.quizapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    String question;
    boolean correctAnswer;

    public Question(String str, boolean bool) {
        this.question = str;
        this.correctAnswer = bool;
    }

    public String getQuestion() {
        return question;
    }

    public boolean checkAnswer(boolean userInput) {
        return userInput == correctAnswer;
    }

    protected Question(Parcel in) {
        question = in.readString();
        correctAnswer = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeByte((byte) (correctAnswer ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
