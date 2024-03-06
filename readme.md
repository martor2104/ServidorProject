Para usuario:

    Post: "/api/v1/auth/signup”	(Registra a un usuario)
	Get: "/api/v1/auth/signin"	(Inicia sesion con un usuario)


Para Reservas:

	Post: "/api/v1/reservas/{mesaId}/reservar" (Crea una reserva)
	Delete: “/api/v1/reservas/cancelar/{reservaId}” (Borra una reserva)
	Put: “/api/v1/reservas/actualizarEstado/{reservaId}” (Actualiza una reserva)
	Get: “/api/v1/reservas/{reservaId}” (Devuelve una reserva por su id)
	Get: “/api/v1/reservas” (Devuelve todas las reservas)
	Get: “/api/v1/reservas/usuarios/{usuarioId}” (Devuelve una reserva por la id del usuario)

Para Mesas:

	Get: “/api/v1/mesas” (Devuelve todas las mesas)
	Get: “/api/v1/mesas/{id}” (Devuelve una mesa por su id)
	Post: “/api/v1/mesas” (Crea una mesa)
	Put: “/api/v1/mesas/{id}” (Actualiza una mesa)
	Delete: “/api/v1/mesas/{id}” (Borra una mesa)
