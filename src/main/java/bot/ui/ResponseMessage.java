package bot.ui;

import bot.task.Task;

import java.util.List;

public class ResponseMessage {

    /**
     * Generates a success message for adding a task.
     *
     * @param task The task that was successfully added.
     * @param taskCount The total number of tasks in the list after the addition.
     * @return A formatted string confirming the task addition and showing the new task count.
     */
    public static String getAddTaskSuccessMessage(Task task, int taskCount) {
        return "Got it. I've added this task: " + task
                + "\nNow you have " + taskCount + " tasks in the list.";
    }

    /**
     * Generates a success message for removing a task.
     *
     * @param task The task that was successfully removed.
     * @param taskCount The total number of tasks remaining in the list.
     * @return A formatted string confirming the task removal and showing the new task count.
     */
    public static String getRemoveTaskSuccessMessage(Task task, int taskCount) {
        return "Noted. I've removed this task: " + task
                + "\nNow you have " + taskCount + " tasks in the list.";
    }

    /**
     * Generates a success message for marking a task as done.
     *
     * @param task The task that was marked as done.
     * @return A formatted string confirming the task has been marked.
     */
    public static String getMarkTaskSuccessMessage(Task task) {
        return "Nice! I've marked this task as done:" + "\n" + task;
    }

    /**
     * Generates a success message for unmarking a task (marking as not done).
     *
     * @param task The task that was marked as not done.
     * @return A formatted string confirming the task has been unmarked.
     */
    public static String getUnmarkTaskSuccessMessage(Task task) {
        return "OK, I've marked this task as not done yet:" + "\n" + task;
    }

    /**
     * Formats a list of tasks into a numbered, human-readable string.
     * If the list is empty, it returns a message indicating no tasks are present.
     *
     * @param taskList The list of tasks to format.
     * @return A numbered string representation of the tasks, or a "No tasks found" message.
     */
    public static String getTaskListMessage(List<Task> taskList) {
        if (taskList.isEmpty()) {
            return "No tasks found";
        }

        StringBuilder sb = new StringBuilder();

        // Iterate task list and print task
        for (int i = 0; i < taskList.size(); i++) {
            int indexNum = i + 1; // Index numbering should start from 1 instead of 0
            sb.append(indexNum).append(". ").append(taskList.get(i)).append("\n");
        }

        return sb.toString();
    }

    /**
     * Generates a message to display the results of a task search.
     *
     * @param taskList The list of tasks that matched the search query.
     * @return A formatted string introducing the search results, followed by the numbered list of matching tasks.
     */
    public static String getSearchTaskListMessage(List<Task> taskList) {
        return "Here are the matching tasks in your list: \n" + getTaskListMessage(taskList);
    }

    public static String getSortListMessage(List<Task> taskList) {
        return "Your list have been sorted: \n" + getTaskListMessage(taskList);
    }
}