package aponte.dev.notebeast.model;

public class UserConfig {
    //Atributos de configuración para la interfaz
    private String theme;
    private int fontSize;
    private String language;
    //Atributos de configuración para el comportamiento de la aplicación
    //Idear diferentes utilidades para el area de notificaciones y como sería su comportamiento
    private boolean notifications;
    //Atributos de configuración para el Timer
    private int defaultStudyMinutes;
    private int defaultBreakMinutes;
    private int alterLeadMinutes;
    //Atributos de configuración para el bloqueo(PIN)
    private int blockPIN;

    public UserConfig(String theme, int fontSize, String language, boolean notifications, int defaultStudyMinutes,
                      int defaultBreakMinutes, int alterLeadMinutes, int blockPIN) {
        this.theme = theme;
        this.fontSize = fontSize;
        this.language = language;
        this.notifications = notifications;
        this.defaultStudyMinutes = defaultStudyMinutes;
        this.defaultBreakMinutes = defaultBreakMinutes;
        this.alterLeadMinutes = alterLeadMinutes;
        this.blockPIN = blockPIN;
    }

    //Getters
    public String getTheme() {
        return theme;
    }

    public int getFontSize() {
        return fontSize;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public int getDefaultStudyMinutes() {
        return defaultStudyMinutes;
    }

    public int getDefaultBreakMinutes() {
        return defaultBreakMinutes;
    }

    public int getAlterLeadMinutes() {
        return alterLeadMinutes;
    }

    public int getBlockPIN() {
        return blockPIN;
    }

    //Setters
    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public void setDefaultStudyMinutes(int defaultStudyMinutes) {
        this.defaultStudyMinutes = defaultStudyMinutes;
    }

    public void setDefaultBreakMinutes(int defaultBreakMinutes) {
        this.defaultBreakMinutes = defaultBreakMinutes;
    }

    public void setAlterLeadMinutes(int alterLeadMinutes) {
        this.alterLeadMinutes = alterLeadMinutes;
    }

    public void setBlockPIN(int blockPIN) {
        this.blockPIN = blockPIN;
    }
}
