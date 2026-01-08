import threading
from Fatura import Fatura


class FaturaConsumer:
    CAMINHO_JSON_FATURAS = "src/main/java/lei/grupo4/resources/Faturas.json"
    _instance = None
    _lock = threading.Lock()

    def __new__(cls, *args, **kwargs):
        if cls._instance is None:
            with cls._lock:
                if cls._instance is None:
                    cls._instance = \
                        super().__new__(cls)
                    cls._instance._inited = False
                # if
            # with
        # if
        return cls._instance

    # def __new__

    def __init__(
            self
    ):
        if not self._inited:
            self._raw_data = \
                FaturaConsumer.consume_json()

            # data => the processed data,
            # organized in a dict
            self._data = \
                Fatura.parse_json_from_file(
                    self._raw_data
                )

            self._inited = True

    # def __init__
    @staticmethod
    def consume_json():
        pass