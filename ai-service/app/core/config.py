from pydantic_settings import BaseSettings


class Settings(BaseSettings):
    postgres_url: str = "postgresql://defer:defer@localhost:5432/defer"
    openai_api_key: str = ""

    class Config:
        env_file = ".env"


settings = Settings()

# --- Scoring thresholds and keyword lists ---

FRUSTRATION_KEYWORDS = [
    "again", "still", "useless", "terrible", "horrible", "worst", "awful",
    "ridiculous", "unacceptable", "pathetic", "incompetent", "waste",
    "angry", "furious", "frustrated", "annoying", "disappointed",
]

FRUSTRATION_ESCALATION_PHRASES = [
    "this is the third time", "i already told you", "how many times",
    "nothing works", "nobody helps", "still not resolved", "keep getting",
    "same issue", "same problem", "going in circles",
]

CONFUSION_KEYWORDS = [
    "confused", "don't understand", "unclear", "what do you mean",
    "i'm lost", "makes no sense", "not sure", "which one", "how do i",
    "what should i", "i thought", "but you said",
]

NEGATIVE_SENTIMENT_KEYWORDS = [
    "bad", "poor", "hate", "never", "broken", "fail", "failed",
    "wrong", "error", "scam", "trash", "garbage", "disgusting",
]

EFFORT_MSG_COUNT_CAP = 10
REPETITION_SIMILARITY_THRESHOLD = 0.85
