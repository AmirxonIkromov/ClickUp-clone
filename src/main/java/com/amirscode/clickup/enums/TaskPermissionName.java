package com.amirscode.clickup.enums;

public enum TaskPermissionName {

    FULL_ACCESS_TASK("Full",
            "Full access. Can create task"
    ),
    CAN_EDIT_TASK("Edit",
            "Can edit. But can't create task"
    ),
    CAN_COMMENT_TASK("Comment",
            "Can comment. If assigned, can change status & assignee(s)"
    ),
    CAN_VIEW_TASK("View Only",
            "Read-only. Can't comment or edit"
    );

    private final String name;
    private final String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    TaskPermissionName(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
