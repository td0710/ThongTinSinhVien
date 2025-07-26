declare module "*.jpg" {
  const src: string;
  export default src;
}
/// <reference types="vite/client" />

interface ImportMetaEnv {
  readonly VITE_API_BASE_URL: string;
  readonly VITE_AZURE_TENANT_ID: string;
  readonly VITE_AZURE_CLIENT_ID: string;
  readonly VITE_AZURE_REDIRECT_URI: string;
}

interface ImportMeta {
  readonly env: ImportMetaEnv;
}
