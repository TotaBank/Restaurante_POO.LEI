from __future__ import annotations
class Fatura:
    def __init__(
            self,
            p_preco:float,
            p_pedido:int,
            p_items:list[dict[str, int]],
            p_metodo_pagamento:str
    ):
        self._preco:float = p_preco
        self._pedido:int = p_pedido
        self._items:list[dict[str,int]] = p_items
        self._metodo_pagamento:str = p_metodo_pagamento

    @staticmethod
    def parse_json_from_file(p_raw_data:dict) -> list[Fatura]:
        pass