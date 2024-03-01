Para usuario:

    Post: "/api/v1/auth/signup”
G   et: "/signin"


Para Reservas:

	Delete raserva: “/api/v1/reservas/cancelar/{reservaId}”
	Put reserva: “/api/v1/reservas/actualizarEstado/{reservaId}”
	Get reserva: “/api/v1/reservas/{reservaId}”
	Get all Reservas: “/api/v1/reservas”
	Get reservas por usuario: “/api/v1/reservas/usuarios/{usuarioId}”

Para Mesas:

	Get all Mesas: “/api/v1/mesas”
	Get mesa: “/api/v1/mesas/{id}”
	Post Mesa: “/api/v1/mesas”
	Put Mesa: “/api/v1/mesas/{id}”
	Delete mesa: “/api/v1/mesas/{id}”
