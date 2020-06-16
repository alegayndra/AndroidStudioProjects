package al.tareas.recyclerview;

public class Clima {
    private String _mClimaUrl;
    private String _mForecastDia;
    private String _mForecastDescripcion;
    private String _mForecastMinMax;
    public Clima(String mClimaUrl, String mForecastDia, String mForecastDescripcion, String mForecastMinMax) {
        this._mClimaUrl = mClimaUrl;
        this._mForecastDia = mForecastDia;
        this._mForecastDescripcion = mForecastDescripcion;
        this._mForecastMinMax = mForecastMinMax;
    }

    public Clima (){
    }
    public String getClimaUrl() {
        return _mClimaUrl;
    }
    public void setClimaUrl(String mClimaUrl) {
        this._mClimaUrl = mClimaUrl;
    }
    public String get_mForecastDia() {
        return _mForecastDia;
    }
    public void setForecastDia(String mForecastDia) {
        this._mForecastDia = mForecastDia;
    }
    public String getForecastDescripcion() {
        return _mForecastDescripcion;
    }
    public void setForecastDescripcion(String mForecastDescripcion) {
        this._mForecastDescripcion = mForecastDescripcion;
    }
    public String getForecastMinMax() {
        return _mForecastMinMax;
    }
    public void setForecastMinMax(String mForecastMinMax) {
        this._mForecastMinMax = mForecastMinMax;
    }




}
