package NetworkIP;

public class Route {
    private final IPAddress network;
    private final IPAddress subnetMask;
    private final int prefixLength;
    private final String nextHop;

    public Route(String cidrNotation, String nextHop) throws IllegalArgumentException {
        String[] parts = cidrNotation.split("/");
        if (parts.length != 2) {
            throw new IllegalArgumentException("无效的CIDR表示法");
        }
        this.network = new IPAddress(parts[0]);
        this.prefixLength = Integer.parseInt(parts[1]);
        if (prefixLength < 0 || prefixLength > 32) {
            throw new IllegalArgumentException("前缀长度必须在0-32之间");
        }
        this.subnetMask = calculateSubnetMask(prefixLength);
        this.nextHop = nextHop;
        IPAddress calculatedNetwork = network.and(subnetMask);
        if (!calculatedNetwork.equals(network)) {
            throw new IllegalArgumentException("网络地址不是有效的CIDR块");
        }
    }

    private IPAddress calculateSubnetMask(int prefixLength) {
        int[] maskOctets = new int[4];
        for (int i = 0; i < 4; i++) {
            if (prefixLength >= 8) {
                maskOctets[i] = 255;
                prefixLength -= 8;
            } else if (prefixLength > 0) {
                maskOctets[i] = 256 - (1 << (8 - prefixLength));
                prefixLength = 0;
            } else {
                maskOctets[i] = 0;
            }
        }
        return new IPAddress(maskOctets);
    }

    public boolean matches(IPAddress ip) {
        IPAddress networkPart = ip.and(subnetMask);
        return networkPart.equals(network);
    }

    public String getNetwork() {
        return network + "/" + prefixLength;
    }

    public String getNextHop() {
        return nextHop;
    }

    public int getPrefixLength() {
        return prefixLength;
    }

    public IPAddress getSubnetMask() {
        return subnetMask;
    }
}