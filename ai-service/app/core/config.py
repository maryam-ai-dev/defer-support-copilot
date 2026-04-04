from pydantic_settings import BaseSettings


class Settings(BaseSettings):
    postgres_url: str = "postgresql://defer:defer@localhost:5432/defer"
    openai_api_key: str = ""

    class Config:
        env_file = ".env"


settings = Settings()
