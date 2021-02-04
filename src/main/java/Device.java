public class Device {
    private DeviceType deviceType;
    private String path;

    public Device() {
        deviceType = null;
        path = null;
    }

    public void setDeviceType(String deviceTypeInput){
        deviceType = deviceTypeConvert(deviceTypeInput);
    }

    public void setPath(String pathInput){
        path = pathInput;
        System.out.println(path);
    }

    private DeviceType deviceTypeConvert(String deviceType) {
        switch (deviceType){
            case "Kindle":
                return DeviceType.KINDLE;
            case "Kobo":
                return DeviceType.KOBO;
            default:
                return null;
        }
    }
}
