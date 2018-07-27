
/* Copyright 2018 Rede S.A.
=============================================
Autor: Jéssica Cruz
Data : 20/07/2018
Empresa : Rede
Descricao: Procedure que retorna lista de Cancelamentos de um determinado periodo
*/

USE [ECDB007]
GO

IF ( OBJECT_ID('dbo.SP_GET_LIST_REFUNDS') IS NOT NULL )
   DROP PROCEDURE dbo.SP_GET_LIST_REFUNDS
GO

CREATE PROC [dbo].[SP_GET_LIST_REFUNDS]
	@dataInicial as datetime,
	@dataValida as bit,
	@AppKey as integer
AS
BEGIN

	DECLARE @dateSearch as datetime

	IF(@dataValida=1)
		BEGIN
			SET @dateSearch=@dataInicial
		END
	ELSE
		BEGIN
			SET @dateSearch= (SELECT MAX(DTH_FIM_PCM) FROM TBEC_LOG_PCM WHERE COD_TIP_PCM=@AppKey)
		END


	SELECT 
	TCAN.[NUM_REF_TRAN],[DAT_CAN],[NUM_UNC_CAN_ADQR],TCAN.[NUM_PDV],[COD_STTU_CAN],[VAL_CAN],[NUM_AVSO_CAN],[DAT_RQSC_CAN],[COD_TIP_CAN]
	FROM [TBEC_SLC_CAN_TRAN_ADQR] TCAN
	INNER JOIN [TRAN_ADQR] TA ON TA.NUM_REF_TRAN=TCAN.NUM_REF_TRAN
	WHERE TCAN.[DAT_TRAN] >= @dateSearch
	ORDER BY TCAN.[DAT_TRAN] DESC

	IF (@@error <> 0)
		SELECT cod_erro = @@ERROR, mensagem = 'Erro Inesperado.'

END


