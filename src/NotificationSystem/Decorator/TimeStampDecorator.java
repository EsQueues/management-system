package NotificationSystem.Decorator;

import NotificationSystem.Observer.UserObserver;
import NotificationSystem.Strategy.NotificationType;

import java.time.LocalDateTime;

public class TimeStampDecorator implements NotificationDecorator {
    private UserObserver wrappedObserver;
    public TimeStampDecorator(UserObserver userObserver){
        this.wrappedObserver=userObserver;
    }
    @Override
    public void handleEvent(String message, NotificationType type) {
        String message1=addTimeStamp(message);
        wrappedObserver.handleEvent(message1,type);
    }

    private String addTimeStamp(String message){
        LocalDateTime timestamp=LocalDateTime.now();
        return "["+timestamp+"] "+message;
    }

}
