package com.example.notificationservice.domain.vo;

public enum NotificationStatus {
    PENDING("발송대기"){
        @Override
        public boolean canTransitionTo(NotificationStatus newStatus){
            return newStatus == SENDING;
        }

        @Override
        public boolean canSend(){
            return true;
        }

        @Override
        public boolean isCancellable(){
            return true;
        }
    },

    SENDING("발송중"){
        @Override
        public boolean canTransitionTo(NotificationStatus newStatus){
            return newStatus == SENT ||
                    newStatus == FAILED;
        }

        @Override
        public boolean canSend() {
            return false;
        }

        @Override
        public boolean isCancellable() {
            return false;
        }
    },

    SENT("발송완료") {
        @Override
        public boolean canTransitionTo(NotificationStatus newStatus) {
            return false;
        }

        @Override
        public boolean canSend() {
            return false;
        }

        @Override
        public boolean isCancellable() {
            return false;
        }
    },

    FAILED("발송실패"){
        @Override
        public boolean canTransitionTo(NotificationStatus newStatus){
            return false;
        }

        @Override
        public boolean canSend() {
            return false;
        }

        @Override
        public boolean isCancellable() {
            return false;
        }
    };

    private final String status;

    NotificationStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public abstract boolean canTransitionTo(NotificationStatus newStatus);

    public abstract boolean canSend();

    public abstract boolean isCancellable();
}
