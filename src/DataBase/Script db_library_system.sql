CREATE TABLE TipoUsuario (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    descripcion TEXT
);

CREATE TABLE Carrera (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT
);

CREATE TABLE Especialidad (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT,
    carrera_id INTEGER,
    FOREIGN KEY (carrera_id) REFERENCES Carrera(id)
);

CREATE TABLE TipoMaterial (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    descripcion TEXT
);

CREATE TABLE Usuario (
    codigo TEXT PRIMARY KEY,
    correo TEXT NOT NULL,
    contrasenia TEXT,
    nombre TEXT,
    apellido TEXT,
    tipo_usuario_id INTEGER,
    carrera_id INTEGER,
    especialidad_id INTEGER,
    FOREIGN KEY (tipo_usuario_id) REFERENCES TipoUsuario(id),
    FOREIGN KEY (carrera_id) REFERENCES Carrera(id),
    FOREIGN KEY (especialidad_id) REFERENCES Especialidad(id)
);

CREATE TABLE Penalidad (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    fechaAsignacion TEXT,
    fechaFinalizacion TEXT,
    motivo TEXT,
    estado TEXT DEFAULT 'activa',
    usuario_codigo TEXT,
    FOREIGN KEY (usuario_codigo) REFERENCES Usuario(codigo)
);

CREATE TABLE TipoReserva (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    descripcion TEXT NOT NULL
);

CREATE TABLE EstadoReserva (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    descripcion TEXT NOT NULL
);

CREATE TABLE Reserva (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    fechaReserva TEXT,
    tipoReserva_id INTEGER,
    estado_id INTEGER,
    usuario_responsable_id TEXT,
    FOREIGN KEY (tipoReserva_id) REFERENCES TipoReserva(id),
    FOREIGN KEY (estado_id) REFERENCES EstadoReserva(id),
    FOREIGN KEY (usuario_responsable_id) REFERENCES Usuario(codigo)
);

CREATE TABLE Material (
    codigo INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo_material_id INTEGER,
    FOREIGN KEY (tipo_material_id) REFERENCES TipoMaterial(id)
);

CREATE TABLE Libro (
    libro_id INTEGER PRIMARY KEY,
    nombre TEXT,
    autor TEXT,
    fecha_publicacion TEXT,
    genero TEXT,
    idioma TEXT,
    ISBN TEXT,
    editorial TEXT,
    edicion TEXT,
    FOREIGN KEY (libro_id) REFERENCES Material(codigo)
);
CREATE TABLE ReservaLibro (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    reserva_id INTEGER NOT NULL,
    libro_id INTEGER NOT NULL,
    FOREIGN KEY (reserva_id) REFERENCES Reserva(id),
    FOREIGN KEY (libro_id) REFERENCES Libro(libro_id)
);

CREATE TABLE Ejemplar (
    ejemplar_id INTEGER PRIMARY KEY AUTOINCREMENT,
    libro_id INTEGER,
    estado TEXT DEFAULT 'disponible',
    FOREIGN KEY (libro_id) REFERENCES Libro(libro_id)
);

CREATE TABLE ArchivoMultimedia (
    archivo_id INTEGER PRIMARY KEY,
    nombre TEXT,
    autor TEXT,
    fecha_publicacion TEXT,
    tamaño TEXT,
    duracion TEXT,
    formato TEXT,
    resolucion TEXT,
    tipoMultimedia TEXT,
    FOREIGN KEY (archivo_id) REFERENCES Material(codigo)
);

CREATE TABLE ArchivoDigital (
    archivo_id INTEGER PRIMARY KEY,
    nombre TEXT,
    autor TEXT,
    formato TEXT,
    tamaño TEXT,
    fechaPublicacion TEXT,
    ruta TEXT,
    FOREIGN KEY (archivo_id) REFERENCES Material(codigo)
);



CREATE TABLE RecursoTecnologico (
    codigo INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo TEXT NOT NULL,
    estado TEXT DEFAULT 'operativo'
);

CREATE TABLE Tablet (
    codigo INTEGER PRIMARY KEY,
    modelo TEXT,
    sistemaOperativo TEXT,
    FOREIGN KEY (codigo) REFERENCES RecursoTecnologico(codigo)
);

CREATE TABLE Computadora (
    codigo INTEGER PRIMARY KEY,
    ram TEXT,
    sistemaOperativo TEXT,
    procesador TEXT,
    FOREIGN KEY (codigo) REFERENCES RecursoTecnologico(codigo)
);

CREATE TABLE ReservaRecursoTecnologico (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    reserva_id INTEGER,
    recurso_id INTEGER,
    fechaHoraInicio TEXT NOT NULL,
    fechaHoraFin TEXT NOT NULL,
    duracionHoras INTEGER NOT NULL,
    FOREIGN KEY (reserva_id) REFERENCES Reserva(id),
    FOREIGN KEY (recurso_id) REFERENCES RecursoTecnologico(codigo)
);

CREATE TABLE Sala (
    codigo INTEGER PRIMARY KEY AUTOINCREMENT,
    nombresala TEXT,
    capacidad INTEGER
);

CREATE TABLE ReservaAmbiente (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    reserva_id INTEGER NOT NULL,
    sala_codigo INTEGER NOT NULL,
    fechaHoraInicio TEXT NOT NULL,
    fechaHoraFin TEXT NOT NULL,
    duracionHoras INTEGER NOT NULL,
    FOREIGN KEY (reserva_id) REFERENCES Reserva(id),
    FOREIGN KEY (sala_codigo) REFERENCES Sala(codigo)
);

CREATE TABLE DetalleReservaAmbienteAlumnos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    reserva_ambiente_id INTEGER NOT NULL,
    usuario_id TEXT NOT NULL,
    FOREIGN KEY (reserva_ambiente_id) REFERENCES ReservaAmbiente(id),
    FOREIGN KEY (usuario_id) REFERENCES Usuario(codigo)
);
