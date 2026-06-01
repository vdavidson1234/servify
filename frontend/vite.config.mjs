import tailwindcss from "@tailwindcss/vite";
import react from "@vitejs/plugin-react";
import { defineConfig, loadEnv } from "vite";

export default defineConfig(({ mode }) => {
  const env = loadEnv(mode, process.cwd(), "");
  const backendUrl = env.VITE_SERVIFY_BACKEND_URL || "http://localhost:8080";

  return {
    plugins: [react(), tailwindcss()],
    optimizeDeps: {
      noDiscovery: true,
      include: [
        "lucide-react",
        "motion/react",
        "react",
        "react-dom/client",
        "react/jsx-dev-runtime",
      ],
    },
    server: {
      port: 5173,
      proxy: {
        "/api": {
          target: backendUrl,
          changeOrigin: true,
        },
      },
    },
  };
});
