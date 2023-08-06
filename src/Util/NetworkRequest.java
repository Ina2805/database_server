package Util;

import java.io.Serializable;

public class NetworkRequest implements Serializable {

    private String object;
    private NetworkType networkType;

    public NetworkRequest(NetworkType networkType, String object) {
        this.object = object;
        this.networkType = networkType;
    }

    public String getObject() {
        return object;
    }

    public NetworkType getNetworkType() {
        return networkType;
    }
    @Override
    public String toString() {
        return "NetworkRequest{" +
                "data='" + object + '\'' +
                ", networkType=" + networkType +
                '}';
    }
}
