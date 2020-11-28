package quintacarmelita.com;

public class Kid {
    public String name;
    public int age;
    //Datos Generales
    public double peso;
    public String talla;
    public String fechaDeIngreso;
    public String fechaDeLLegada;
    public String fechaDeNacimiento;
    public String status;
    //Datos Médicos
    public String alergias;
    public String medicamentos;
    public String reporteDoctores;
    public String reportePsicologo;
    public String doctorEncargado;
    public String psicologoEncargdo;
    //Calendario
    //Bitácora
    public String conductasPositivas;
    public String conductasNegativas;
    public String incidentes;
    public String areaFisica;
    public String consecuencias;
    public String comentarios;

    public Kid(){
        //ksdgjksd
        //public no-arg constructor needed
    }

    public Kid(String name, int age, double peso, String talla, String fechaDeIngreso, String fechaDeLLegada, String fechaDeNacimiento,
               String status, String alergias, String medicamentos, String reporteDoctores, String reportePsicologo, String doctorEncargado,
               String psicologoEncargdo,String conductasPositivas, String conductasNegativas, String incidentes, String areaFisica,
               String consecuencias, String comentarios){
        this.name= name;
        this.age= age;
        //Datos Generales
        this.peso= peso;
        this.talla= talla;
        this.fechaDeIngreso= fechaDeIngreso;
        this.fechaDeLLegada= fechaDeLLegada;
        this.fechaDeNacimiento= fechaDeNacimiento;
        this. status= status;
        //Datos Médicos
        this.alergias= alergias;
        this.medicamentos= medicamentos;
        this.reporteDoctores= reporteDoctores;
        this.reportePsicologo= reportePsicologo;
        this.doctorEncargado= doctorEncargado;
        this.psicologoEncargdo= psicologoEncargdo;
        //Calendario
        //Bitácora
        this.conductasPositivas= conductasPositivas;
        this.conductasNegativas= conductasNegativas;
        this.incidentes= incidentes;
        this.areaFisica= areaFisica;
        this.consecuencias= consecuencias;
        this.comentarios= comentarios;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setAge(int age){
        this.age=age;
    }

    public int getAge(){
        return age;
    }

    //Datos Generales
    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public void setFechaDeIngreso(String fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public String getFechaDeLLegada() {
        return fechaDeLLegada;
    }


    public void setFechaDeLLegada(String fechaDeLLegada) {
        this.fechaDeLLegada = fechaDeLLegada;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getReporteDoctores() {
        return reporteDoctores;
    }

    public void setReporteDoctores(String reporteDoctores) {
        this.reporteDoctores = reporteDoctores;
    }

    public String getReportePsicologo() {
        return reportePsicologo;
    }

    public void setReportePsicologo(String reportePsicologo) {
        this.reportePsicologo = reportePsicologo;
    }

    public String getDoctorEncargado() {
        return doctorEncargado;
    }

    public void setDoctorEncargado(String doctorEncargado) {
        this.doctorEncargado = doctorEncargado;
    }

    public String getPsicologoEncargdo() {
        return psicologoEncargdo;
    }

    public void setPsicologoEncargdo(String psicologoEncargdo) {
        this.psicologoEncargdo = psicologoEncargdo;
    }

    public String getConductasPositivas() {
        return conductasPositivas;
    }

    public void setConductasPositivas(String conductasPositivas) {
        this.conductasPositivas = conductasPositivas;
    }

    public String getConductasNegativas() {
        return conductasNegativas;
    }

    public void setConductasNegativas(String conductasNegativas) {
        this.conductasNegativas = conductasNegativas;
    }

    public String getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(String incidentes) {
        this.incidentes = incidentes;
    }

    public String getAreaFisica() {
        return areaFisica;
    }

    public void setAreaFisica(String areaFisica) {
        this.areaFisica = areaFisica;
    }

    public String getConsecuencias() {
        return consecuencias;
    }

    public void setConsecuencias(String consecuencias) {
        this.consecuencias = consecuencias;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

}