package NetworkIP;

public class IPAddress {
    private final int[] octets = new int[4];

    public IPAddress(String address) throws IllegalArgumentException {
        if (!address.matches("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$")) {
            throw new IllegalArgumentException("无效的IP地址格式");
        }
        String[] parts = address.split("\\.");
        for (int i = 0; i < 4; i++) {
            octets[i] = Integer.parseInt(parts[i]);
            if (octets[i] < 0 || octets[i] > 255) {
                throw new IllegalArgumentException("IP地址各段必须在0-255之间");
            }
        }
    }

    public IPAddress(int[] octets) {
        System.arraycopy(octets, 0, this.octets, 0, 4);
    }

    public IPAddress and(IPAddress other) {
        int[] result = new int[4];
        for (int i = 0; i < 4; i++) {
            result[i] = this.octets[i] & other.octets[i];
        }
        return new IPAddress(result);
    }

    public boolean equals(IPAddress other) {
        for (int i = 0; i < 4; i++) {
            if (this.octets[i] != other.octets[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%d.%d.%d.%d", octets[0], octets[1], octets[2], octets[3]);
    }
}