from __future__ import annotations
class Fatura:
    def __init__(
            self,
            p_id:int,
            p_preco:float,
            p_pedido:int,
            p_items:dict[str, float],
            p_metodo_pagamento:str
    ):
        self._id:int = p_id
        self._preco:float = p_preco
        self._pedido:int = p_pedido
        self._items:dict[str,float] = p_items
        self._metodo_pagamento:str = p_metodo_pagamento

    @staticmethod
    def parse_json_from_file(p_data:dict) -> list[Fatura]:
        ret:list[Fatura] = []
        fatura:list = list (p_data.keys())
        for id in fatura:
            dados_fatura:dict = p_data[id]
            id:int = id
            preco:float = dados_fatura["Preco"]
            pedido:int = dados_fatura["Pedido"]
            items:dict[str, float] = dados_fatura["Items"]
            metodo_pagamento:str = dados_fatura["Metodo_Pagamento"]

            fatura:Fatura = Fatura(id, preco, pedido, items, metodo_pagamento)
            ret.append(fatura)

        return ret

    def __str__(self):
        return f"Id: {self._id}\n"+\
        f"Preco: {self._preco} ; Pedido: {self._pedido} ; Items: {self._items} ; Metodo de Pagamento: {self._metodo_pagamento}"

    def __repr__(self):
        return self.__str__()

