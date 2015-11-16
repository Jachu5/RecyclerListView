package demo.wunderlist.alfredo_cerezo.wunderlist_demo.adapter;

import java.util.List;

import demo.wunderlist.alfredo_cerezo.wunderlist_demo.android.database.TaskDatabaseAdapter;
import demo.wunderlist.alfredo_cerezo.wunderlist_demo.core.entities.Observer;
import demo.wunderlist.alfredo_cerezo.wunderlist_demo.core.entities.Task;
import demo.wunderlist.alfredo_cerezo.wunderlist_demo.core.gateways.TaskGateway;
import demo.wunderlist.alfredo_cerezo.wunderlist_demo.exceptions.WunderlistException;

/**
 * Created by alfredocerezoluna on 16/11/15.
 */
public class TaskAdapter implements TaskGateway {

    private TaskDatabaseAdapter mTaskDatabaseAdapter;

    public TaskAdapter(TaskDatabaseAdapter taskAdapter) {
        this.mTaskDatabaseAdapter = taskAdapter;
    }

    @Override
    public void createTask(Task task, Observer<Void> observer) {
        try {
            mTaskDatabaseAdapter.createTask(task);
        } catch (Exception exception) {
            observer.onError(new WunderlistException());
        }
    }

    @Override
    public void getAllTasks(Observer<List<Task>> allTask) {

    }
}
