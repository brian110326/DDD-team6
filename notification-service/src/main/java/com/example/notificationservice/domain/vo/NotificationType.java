package com.example.notificationservice.domain.vo;

public enum NotificationType {

    EMAIL("이메일_발송"){
        @Override
        public int getMaxLength(){
            return Integer.MAX_VALUE;
        }
        @Override
        public boolean requiresEmail(){
            return true;
        }
        @Override
        public boolean requiresPhone(){
            return false;
        }
    },
    KAKAO("카카오_발송"){
        @Override
        public int getMaxLength(){
            return 1000;
        }
        @Override
        public boolean requiresEmail(){
            return false;
        }
        @Override
        public boolean requiresPhone(){
            return true;
        }
    };

    private final String description;

    NotificationType(String description){
        this.description = description;
    }

    public abstract int getMaxLength();
    public abstract boolean requiresEmail();
    public abstract boolean requiresPhone();

}
