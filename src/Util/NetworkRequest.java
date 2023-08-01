package Util;

import java.io.Serializable;

public class NetworkRequest implements Serializable {

    private String data;
    private NetworkType networkType;

    public NetworkRequest(String data, NetworkType networkType) {
        this.data = data;
        this.networkType = networkType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public NetworkType getNetworkType() {
        return networkType;
    }

    public void setNetworkType(NetworkType networkType) {
        this.networkType = networkType;
    }

    @Override
    public String toString() {
        return "NetworkRequest{" +
                "data='" + data + '\'' +
                ", networkType=" + networkType +
                '}';
    }
}
