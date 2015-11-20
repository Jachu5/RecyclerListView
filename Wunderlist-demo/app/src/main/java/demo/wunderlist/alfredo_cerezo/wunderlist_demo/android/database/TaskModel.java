package demo.wunderlist.alfredo_cerezo.wunderlist_demo.android.database;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.NotNull;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by alfredocerezoluna on 16/11/15.
 */

@Table(databaseName = Database.NAME)
public class TaskModel extends BaseModel {

    @Column(name = "id")
    @PrimaryKey
    @NotNull
    String mId;

    @Column(name = "position")
    @NotNull
    int mPosition;

    @Column(name = "completed")
    @NotNull
    boolean mCompleted;

    @Column(name = "content")
    @NotNull
    String mContent;

    public TaskModel() {
        super();
    }

    public String getId() {
        return mId;
    }

    public void setId(String mTaskId) {
        this.mId = mTaskId;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        this.mPosition = position;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean mCompleted) {
        this.mCompleted = mCompleted;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }
}
