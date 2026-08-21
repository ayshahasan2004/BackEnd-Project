# Where the APIs live — quick guide

Ignore everything else in the repo. These are the only files that touch the backend.

## Frontend (Angular) — files that call the API

| File | What it does |
|---|---|
| `src/app/services/auth.service.ts` | Calls `POST /api/auth/signup` and `/login`, stores the token |
| `src/app/services/menu.service.ts` | Calls `GET /api/menu` |
| `src/app/pages/login-page/login-page.component.ts` | Uses `auth.service.ts` to log in |
| `src/app/pages/signup-page/signup-page.component.ts` | Uses `auth.service.ts` to sign up |
| `src/app/pages/reserve-page/reserve-page.component.ts` | Calls `POST /api/reservations` |
| `src/app/pages/order-page/order-page.component.ts` | Calls `POST /api/orders` |
| `src/app/interceptors/auth.interceptor.ts` | Auto-attaches the saved token to every request |
| `src/app/guards/auth.guard.ts` | Blocks `/reserve` and `/order` unless logged in |
| `proxy.conf.json` | Forwards `/api/*` to the backend during `npm start` |

Everything else (directives, pipes, hero/room/gallery/statement components, styling) is pure UI — no backend involved, safe to ignore for this work.

