package subscriber.client;

import java.util.List;

public interface Client<T> {

    void send(List<T> prices);
}
