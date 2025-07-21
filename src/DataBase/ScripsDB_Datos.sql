Select rl.reserva_id, r.fechaReserva,er.estado,
                r.usuario_responsable_id, 
                l.libro_id,
                e.ejemplar_id from ReservaLibro rl
                join Reserva r on r.id = rl.reserva_id
                join EstadoReserva er on er.id = r.estado_id
                join Ejemplar e on e.ejemplar_id = rl.ejemplar_id
                join Libro l on l.libro_id = e.libro_id
                where rl.reserva_id = 9
				
				
SELECT RL.libro_id, R.id, R.fechaReserva, R.usuario_responsable_id, " +
                     "ER.estado, TR.tipo_reserva " +
                     "FROM Reserva R " +
                     "JOIN ReservaLibro RL ON R.id = RL.reserva_id " +
                     "JOIN EstadoReserva ER ON R.estado_id = ER.id " +
                     "JOIN TipoReserva TR ON R.tipoReserva_id = TR.id " +
                     "WHERE R.id = ? AND TR.tipo_reserva = 'Libro'
					 
					 
Select * from Reserva where id=9

SELECT RT.recurso_id, RT.fechaHoraInicio, RT.fechaHoraFin, RT.duracionHoras, 
                R.id, R.fechaReserva, R.usuario_responsable_id, 
                ER.estado, TR.tipo_reserva 
                FROM Reserva R 
                JOIN ReservaRecursoTecnologico RT ON R.id = RT.reserva_id 
                JOIN EstadoReserva ER ON R.estado_id = ER.id 
                JOIN TipoReserva TR ON R.tipoReserva_id = TR.id 
                WHERE R.id = 1
				
				
				
INSERT INTO RecursoTecnologico (codigo, tipo, estado) VALUES (1, 'tablet', 'operativo');
INSERT INTO RecursoTecnologico (codigo, tipo, estado) VALUES (2, 'tablet', 'operativo');
INSERT INTO RecursoTecnologico (codigo, tipo, estado) VALUES (3, 'computadora', 'operativo');
INSERT INTO RecursoTecnologico (codigo, tipo, estado) VALUES (4, 'computadora', 'operativo');

INSERT INTO Tablet (codigo, modelo, sistemaOperativo) VALUES (1, 'Samsung Galaxy Tab A8', 'Android 12');
INSERT INTO Tablet (codigo, modelo, sistemaOperativo) VALUES (2, 'iPad 9ª Gen', 'iPadOS 15');
INSERT INTO Computadora (codigo, ram, sistemaOperativo, procesador) 
VALUES (3, '8 GB', 'Windows 10', 'Intel Core i5');

INSERT INTO Computadora (codigo, ram, sistemaOperativo, procesador) 
VALUES (4, '16 GB', 'Ubuntu 22.04', 'AMD Ryzen 5');


INSERT INTO Sala (codigo, nombresala, capacidad) VALUES (1, 'Sala de Lectura A', 4);
INSERT INTO Sala (codigo, nombresala, capacidad) VALUES (2, 'Sala de Investigación', 5);
INSERT INTO Sala (codigo, nombresala, capacidad) VALUES (3, 'Sala Multimedia', 4);
INSERT INTO Sala (codigo, nombresala, capacidad) VALUES (4, 'Sala de Estudios Grupales', 5);
INSERT INTO Sala (codigo, nombresala, capacidad) VALUES (5, 'Sala Silenciosa', 5);

INSERT into ReservaRecursoTecnologico (reserva_id,recurso_id,duracion) values (?,?,?);

Select rrt.id as 'cod_reservaRTec',
 rrt.reserva_id as 'cod_reserva_id',
 rrt.recurso_id,
 rrt.duracionHoras,
r.fechaReserva,
er.estado,
r.usuario_responsable_id 
from ReservaRecursoTecnologico rrt
join Reserva r on r.id = rrt.reserva_id
join EstadoReserva er on er.id = r.estado_id
Select codigo, tipo, estado from RecursoTecnologico

delete from Reserva where id = 12

