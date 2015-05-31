package com.mu.mothersunited.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public final class Question implements Parcelable
{
    @SerializedName(value="_id")
    public String questionId;

    public long time;

    public String creatorId;

    public String creatorName;

    public int creatorAge;

    public String title;

    public List<User> upVotes;

    public List<User> downVotes;

    public int creatorPregnancyMonth;

    public List<String> visibleFacebookIds;

    public Question()
    {
        upVotes = new ArrayList<>();
        downVotes = new ArrayList<>();
    }

    protected Question(Parcel in) {
        questionId = in.readString();
        time = in.readLong();
        creatorId = in.readString();
        creatorName = in.readString();
        creatorAge = in.readInt();
        title = in.readString();
        if (in.readByte() == 0x01) {
            upVotes = new ArrayList<User>();
            in.readList(upVotes, User.class.getClassLoader());
        } else {
            upVotes = null;
        }
        if (in.readByte() == 0x01) {
            downVotes = new ArrayList<User>();
            in.readList(downVotes, User.class.getClassLoader());
        } else {
            downVotes = null;
        }
        creatorPregnancyMonth = in.readInt();
        if (in.readByte() == 0x01) {
            visibleFacebookIds = new ArrayList<String>();
            in.readList(visibleFacebookIds, String.class.getClassLoader());
        } else {
            visibleFacebookIds = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(questionId);
        dest.writeLong(time);
        dest.writeString(creatorId);
        dest.writeString(creatorName);
        dest.writeInt(creatorAge);
        dest.writeString(title);
        if (upVotes == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(upVotes);
        }
        if (downVotes == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(downVotes);
        }
        dest.writeInt(creatorPregnancyMonth);
        if (visibleFacebookIds == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(visibleFacebookIds);
        }
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
