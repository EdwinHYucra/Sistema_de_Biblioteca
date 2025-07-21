INSERT INTO TipoUsuario (descripcion) VALUES 
('Administrador'),
('Alumno'),
('Docente'),
('Recepcionista');

-- Insertar carrera
INSERT INTO Carrera (nombre) VALUES 
('Ingeniería de Sistemas e Informática');

-- Insertar especialidades (asociadas a la carrera con id 1)
INSERT INTO Especialidad (nombre, carrera_id) VALUES 
('Desarrollo de Software', 1),
('Redes y Comunicaciones', 1);

-- Alumno con carrera y sin especialidad
INSERT INTO Usuario (codigo, correo, contrasenia, nombre, apellido, tipo_usuario_id, carrera_id, especialidad_id) VALUES 
('A001', 'alumno@ejemplo.com', 'pass123', 'Luis', 'Sánchez', 2, 1, NULL);

-- Docente con especialidad y sin carrera
INSERT INTO Usuario (codigo, correo, contrasenia, nombre, apellido, tipo_usuario_id, carrera_id, especialidad_id) VALUES 
('D001', 'docente@ejemplo.com', 'abc456', 'Carmen', 'Gómez', 3, NULL, 1);

-- Recepcionista sin carrera ni especialidad
INSERT INTO Usuario (codigo, correo, contrasenia, nombre, apellido, tipo_usuario_id, carrera_id, especialidad_id) VALUES 
('R001', 'recep@ejemplo.com', 'xyz789', 'Carlos', 'Pérez', 4, NULL, NULL);

-- Administrador sin carrera ni especialidad
INSERT INTO Usuario (codigo, correo, contrasenia, nombre, apellido, tipo_usuario_id, carrera_id, especialidad_id) VALUES 
('ADM1', 'admin@ejemplo.com', 'admin123', 'Lucía', 'Mendoza', 1, NULL, NULL);